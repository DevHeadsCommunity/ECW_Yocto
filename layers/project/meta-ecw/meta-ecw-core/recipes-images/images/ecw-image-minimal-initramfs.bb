# Simple initramfs image. Mostly used for live images.
SUMMARY = "Small image capable of booting a device."
DESCRIPTION = "Small image capable of booting a device. \
Based on layers/third-party/poky/meta/recipes-core/images/core-image-minimal-initramfs.bb"

INITRAMFS_SCRIPTS ?= "\
                      initramfs-framework-base \
                      initramfs-module-udev \
                      initramfs-module-install-efi \
                      initramfs-module-ecw-nfsroot \
                     "

PACKAGE_INSTALL = "${INITRAMFS_SCRIPTS} ${VIRTUAL-RUNTIME_base-utils} udev base-passwd ${ROOTFS_BOOTSTRAP_INSTALL}"

# Do not pollute the initrd image with rootfs features
IMAGE_FEATURES = ""

# Don't allow the initramfs to contain a kernel
PACKAGE_EXCLUDE = "kernel-image-*"

IMAGE_NAME_SUFFIX ?= ""
IMAGE_LINGUAS = ""

LICENSE = "MIT"

IMAGE_FSTYPES = "${INITRAMFS_FSTYPES}"
inherit core-image

IMAGE_ROOTFS_SIZE = "8192"
IMAGE_ROOTFS_EXTRA_SPACE = "0"

# Use the same restriction as initramfs-module-install
COMPATIBLE_HOST = '(x86_64.*|i.86.*|arm.*|aarch64.*|loongarch64.*|riscv64.*)-(linux.*|freebsd.*)'
