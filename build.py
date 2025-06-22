import os
import threading
import subprocess

apps = [
    "order-service",
    "orchestrator-service",
    "product-validation-service",
    "payment-service",
    "inventory-service"
]

def build_application(app):
    print(f"Building application {app}")
    subprocess.run(
        f"cd {app} && ./gradlew build -x test --parallel",
        shell=True, check=True
    )
    print(f"Application {app} finished building!")

def docker_compose_up():
    print("Running containers!")
    os.system("docker compose up --build -d")
    print("Pipeline finished!")

def build_all_applications():
    print("Starting to build applications!")
    threads = []
    for app in apps:
        t = threading.Thread(target=build_application, args=(app,))
        t.start()
        threads.append(t)
    for t in threads:
        t.join()

def remove_remaining_containers():
    print("Removing all containers.")
    os.system("docker compose down")
    containers = os.popen('docker ps -aq').read().split('\n')
    containers = [c for c in containers if c]
    if containers:
        print(f"There are still {len(containers)} containers created")
        for container in containers:
            print(f"Stopping container {container}")
            os.system(f"docker container stop {container}")
        os.system("docker container prune -f")

if __name__ == "__main__":
    print("Pipeline started!")
    build_all_applications()
    remove_remaining_containers()
    threading.Thread(target=docker_compose_up).start()