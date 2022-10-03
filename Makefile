run-dist:
		./build/install/

install:
		./gradlew clean install

run:
		./gradlew run

report:
		./gradlew jacocoTestReport

lint:
		./gradlew checkstyleMain checkstyleTest

test:
		./gradlew test

.PHONY: build

build:
		./gradlew build
