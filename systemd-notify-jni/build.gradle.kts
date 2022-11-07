plugins {
    `cpp-library`
}

library {
    linkage.set(listOf(Linkage.SHARED))
    targetMachines.set(listOf(machines.linux.x86_64))
    baseName.set("systemd-notify")
}

tasks.withType(LinkSharedLibrary::class) {
    linkerArgs.add("-lsystemd")
}

// prerequisites: sudo apt install g++ libsystemd-dev

// build .so file on linux:

// ../gradlew linkRelease
// or
// g++ -shared -o libsystemd-notify.so systemd-notify.cpp -lsystemd -I .