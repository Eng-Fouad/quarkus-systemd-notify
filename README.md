# quarkus-systemd-notify Project

This project demonstrates integrating systemd-notify with Quarkus.

## Compiling native library

You can compile `systemd-notify.so` library on Ubuntu 22.04 x64 as follows:

```shell script
sudo apt install g++ libsystemd-dev
cd systemd-notify-jni
../gradlew linkRelease
```

or use the file located at:

> ./deployment/opt/quarkus/libs/systemd-notify.so

## Packaging the application

The application can be packaged using:

```shell script
./gradlew build
```

It produces the `quarkus-run.jar` file in the `build/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `build/quarkus-app/lib/` directory.

## Preparing the environment

Unpack JRE 17 at:

> /opt/quarkus/jre/17

Copy following directories from ./deployment to your server:

> /opt/quarkus/commands/
> /opt/quarkus/configs/
> /opt/quarkus/libs/

Copy contents of `./build/quarkus-app/` into:

> /opt/quarkus/deployments/1.0/

Copy service file to your server:

> /etc/systemd/system/quarkus.service

Create log directory:

```shell script
sudo mkdir -p /var/log/quarkus
```

Create `quarkus` user and give it the necessary permissions:

    sudo adduser --disabled-password --gecos "" quarkus-user
    sudo groupadd quarkus-group
    sudo usermod -a -G quarkus-group quarkus-user
    sudo chown quarkus-user:quarkus-group /var/log/quarkus/
    sudo chown -R quarkus-user:quarkus-group /opt/quarkus/*
    sudo find /opt/quarkus -type d -exec chmod 775 {} \;
    sudo find /opt/quarkus -type f -exec chmod 664 {} \;

- Enable systemd service:

```shell script
sudo systemctl enable quarkus
```

## Start/Stop/Restart the systemd service:

```shell script
sudo systemctl start quarkus
sudo systemctl stop quarkus
sudo systemctl restart quarkus
```
