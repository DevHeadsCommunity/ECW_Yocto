SUMMARY = "ECW initramfs module: DHCP + NFSv4 root mount"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

inherit allarch

RDEPENDS:${PN} += "initramfs-framework-base"

SRC_URI += "file://80-ecw-nfsroot.sh"

do_install() {
    install -d ${D}/init.d
    install -m 0755 ${WORKDIR}/80-ecw-nfsroot.sh ${D}/init.d/80-ecw-nfsroot.sh
}

FILES:${PN} += " \
    /init.d/80-ecw-nfsroot.sh \
"