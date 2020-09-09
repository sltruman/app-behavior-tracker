#!/bin/sh
source ./path.sh

$em/emulator -memory 1024 -partition-size 600 -sysdir $sys -system $sys/system.img -data $sys/userdata.img -ramdisk $sys/ramdisk.img -kernel $ken/kernel-qemu-armv7

