IMAGE_NAME = emos-api
IMAGE_VERSION = v1

stop:
	docker rm -f $(IMAGE_NAME) || true

clear:
	docker images | grep -E "$(IMAGE_NAME)" | awk '{print $3}' | uniq | xargs -I {} docker rmi --force {}

build:
	docker build -t $(IMAGE_NAME):$(IMAGE_VERSION) .

tag:

run:

all:
	stop
	clear
	build
	tag