# ═══════════════════════════════════════════════════════════════════════════════
# Stage 1 – Build
# ═══════════════════════════════════════════════════════════════════════════════
FROM eclipse-temurin:21-jdk-alpine AS builder

WORKDIR /workspace

# Copy Gradle wrapper and dependency declarations first to leverage layer cache.
# Re-downloading dependencies only happens when build.gradle changes.
COPY gradlew gradlew.bat settings.gradle build.gradle ./
COPY gradle ./gradle
RUN chmod +x gradlew && ./gradlew dependencies --no-daemon -q

# Copy source and produce the jar
COPY src ./src
RUN ./gradlew jar --no-daemon -q

# ═══════════════════════════════════════════════════════════════════════════════
# Stage 2 – Runtime
# ═══════════════════════════════════════════════════════════════════════════════
FROM eclipse-temurin:21-jre-alpine AS runtime

WORKDIR /app
COPY --from=builder /workspace/build/libs/*.jar app.jar

# ── Heap ────────────────────────────────────────────────────────────────────────
ENV JVM_XMS="64m"
ENV JVM_XMX="256m"

# ── Garbage Collector ───────────────────────────────────────────────────────────
# Supported values (override at runtime):
#   G1GC   →  -XX:+UseG1GC        (default, balanced throughput + low latency)
#   ZGC    →  -XX:+UseZGC         (ultra-low pause, Java 21 production-ready)
#   Parallel → -XX:+UseParallelGC (max throughput, batch workloads)
#   Serial →  -XX:+UseSerialGC    (minimal footprint, single-core/tiny heaps)
ENV JVM_GC="-XX:+UseG1GC"

# ── GC Tuning ───────────────────────────────────────────────────────────────────
# These defaults are calibrated for G1GC; override when switching GC.
ENV JVM_GC_OPTS="\
  -XX:MaxGCPauseMillis=200 \
  -XX:G1HeapRegionSize=4m \
  -XX:G1NewSizePercent=5 \
  -XX:G1MaxNewSizePercent=60 \
  -XX:InitiatingHeapOccupancyPercent=45"

# ── GC Logging ──────────────────────────────────────────────────────────────────
ENV JVM_GC_LOG="-Xlog:gc*:stdout:time,uptime,level,tags"

# ── JIT / Compiler ──────────────────────────────────────────────────────────────
ENV JVM_JIT_OPTS="\
  -XX:+TieredCompilation \
  -XX:CompileThreshold=1500"

# ── Diagnostics & Safety ────────────────────────────────────────────────────────
ENV JVM_DIAG_OPTS="\
  -XX:+HeapDumpOnOutOfMemoryError \
  -XX:HeapDumpPath=/tmp/heapdump.hprof \
  -XX:+ExitOnOutOfMemoryError \
  -Dfile.encoding=UTF-8"

# ── Extra / pass-through ────────────────────────────────────────────────────────
# Append any additional flags at docker run / compose time.
ENV JVM_EXTRA_OPTS=""

# ── Application mode ────────────────────────────────────────────────────────────
# Values: single | multi
ENV APP_MODE="single"

ENTRYPOINT ["sh", "-c", \
  "exec java \
    -Xms${JVM_XMS} \
    -Xmx${JVM_XMX} \
    ${JVM_GC} \
    ${JVM_GC_OPTS} \
    ${JVM_GC_LOG} \
    ${JVM_JIT_OPTS} \
    ${JVM_DIAG_OPTS} \
    ${JVM_EXTRA_OPTS} \
    -jar /app/app.jar ${APP_MODE}"]

