# syntax=docker/dockerfile:1
FROM azul/zulu-openjdk-alpine:11 as build
WORKDIR /workspace/app
COPY . /workspace/app
RUN --mount=type=cache,target=/root/.gradle ./gradlew clean bootJar
RUN mkdir -p build/dependency && (cd build/dependency; jar -xf ../libs/*.jar)

FROM azul/zulu-openjdk-alpine:11 as local
VOLUME /tmp
ARG DEPENDENCY=/workspace/app/build/dependency
COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app
ENTRYPOINT ["java", "-cp", "app:app/lib/*", "i05.a507.certblock.CertblockApplication"]

FROM azul/zulu-openjdk-alpine:11 as prod
VOLUME /tmp
ARG DEPENDENCY=/workspace/app/build/dependency
COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app
ENTRYPOINT ["java", "-cp", "app:app/lib/*", "i05.a507.certblock.CertblockApplication"]
