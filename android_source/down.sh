#!/bin/sh
./repo init -u https://android.googlesource.com/platform/manifest.git -b android-4.2.2_r1
./repo sync
while [ $? -ne 0 ]
do
 ./repo sync
done

source build/envsetup.sh
lunch full-eng
make -j2
