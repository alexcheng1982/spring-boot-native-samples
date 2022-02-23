grpc-native:
	cd grpc && \
		mvn package -Pnative

grpc-native-no-tests:
	cd grpc && \
		mvn package -DskipTests -Pnative

jpa-webmvc-native:
	cd jpa-webmvc && \
		mvn package -Pnative

jpa-webmvc-native-no-tests:
	cd jpa-webmvc && \
    	mvn package -DskipTests -Pnative
