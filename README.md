Prime Numbers Benchmark
=======================

A small Java benchmark that counts prime numbers. The project contains a single-core and a multi-core implementation and a few unit tests.

Purpose
-------
- Provide a tiny, self-contained benchmark to compare single-threaded and parallel prime counting.
- Demonstrate JVM tuning via Docker (heap size, GC selection and options).

Build
-----
Requires Java and Gradle (wrapper provided).

From repository root:

```bash
# build the jar using the Gradle wrapper
./gradlew clean jar
```

Run
---
The jar supports two modes: `single` and `multi`.

Usage:

```bash
# show usage
java -jar build/libs/primenumbers-benchmark-0.0.1-SNAPSHOT.jar

# single-core with default limit
java -jar build/libs/primenumbers-benchmark-0.0.1-SNAPSHOT.jar single

# single-core with custom limit
java -jar build/libs/primenumbers-benchmark-0.0.1-SNAPSHOT.jar single 1000000

# multi-core with custom limit and thread count
java -jar build/libs/primenumbers-benchmark-0.0.1-SNAPSHOT.jar multi 1000000 4
```

Docker
------
This repository includes a Dockerfile and `docker-compose.yml` with several example configurations.
The image exposes environment variables to control JVM options (heap and GC tuning):

- `JVM_XMS` - initial heap size (e.g. `64m`)
- `JVM_XMX` - max heap size (e.g. `1g`)
- `JVM_GC`  - GC selection (e.g. `-XX:+UseG1GC` or `-XX:+UseZGC`)
- `JVM_GC_OPTS` - additional GC tuning options
- `APP_MODE` - `single` or `multi`

Examples:

```bash
# Build the docker image
docker build -t primenumbers-benchmark:latest .

# Run single-core using the defaults from Dockerfile
docker run --rm primenumbers-benchmark:latest

# Run multi-core with a larger heap
docker run --rm -e APP_MODE=multi -e JVM_XMX=1g primenumbers-benchmark:latest

# Use docker-compose to run one of the predefined configs
docker compose up --build single-g1gc
```

Code improvements
----------------
- `Prime` now implements a simple sieve for counting primes and locating the nth prime (faster than repeated trial division for larger limits).
- `isPrime` remains available for tests and small checks.
- `Main` supports a clearer CLI and forwards arguments to the single/multi runners.
- `SingleCorePrimeNumber` and `PrimeNumberMulticore` accept optional limits and, for the multi-mode, a thread count.

Notes
-----
- The sieve implementation uses arrays and may consume significant memory for very large limits (e.g. 100M). Adjust heap settings when running large benchmarks.
- The Dockerfile and docker-compose include sensible defaults and examples for GC tuning and heap sizing.

License
-------
Add your preferred license if you plan to publish this project publicly.

