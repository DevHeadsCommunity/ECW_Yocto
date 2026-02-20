SUMMARY = "The ECW Core image."
LICENSE = "MIT"

inherit core-image

# Allow for machine specific recipes
include ${THISDIR}/${PN}/${MACHINE}/machine-extra-recipes.inc

CORE_OS = " \
    packagegroup-core-boot \
    tzdata \
    bash \
"

ECW_CORE = " \
    htop \
    iperf3 \
    libgpiod \
    libgpiod-tools \
    nmon \
    procps \
    opkg \
    tmux \
    strace \
"

ECW_LOCAL = " \
    ecw-users \
"

IMAGE_INSTALL += " \
    ${CORE_OS} \
    ${ECW_CORE} \
    ${ECW_LOCAL} \
"

# This is the initial minimal image that will mount the NFS root file systems
IMAGE_DEPENDS = "ecw-image-minimal-initramfs"

export IMAGE_BASENAME = "ecw_core"

