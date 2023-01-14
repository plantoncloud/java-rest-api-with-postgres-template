.PHONY: build
build:
	./gradlew build

.PHONY: test
test:
	./gradlew check

.PHONY: run
run:
	./gradlew bootRun

.PHONY: build-image
build-image:
	docker build --platform linux/amd64 -t java-todo-api .
