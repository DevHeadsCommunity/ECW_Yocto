DESCRIPTION = "Add ECW users to the image"
SUMMARY = "Add ECS users to the image"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

RDEPENDS:${PN} += " \
    bash \
"

SRC_URI += " \
    file://bash_aliases \
    file://bash_profile \
    file://bashrc \
"

inherit useradd
USERADD_PACKAGES = "${PN}"

ECW_USER = "ecwuser"
ECW_UID = "1000"
ECW_GID = "1000"

USERADD_PARAM:${PN} = "-u ${ECW_UID} -d /home/${ECW_USER} -r -s /bin/bash -p '${ECW_USER_PASSWORD}' ${ECW_USER}"

do_install(){
    install -d -m 0755 -o ${ECW_UID} ${D}/home/${ECW_USER}
    install -m 0644 -o ${ECW_UID} ${WORKDIR}/bash_aliases ${D}/home/${ECW_USER}/.bash_aliases
    install -m 0644 -o ${ECW_UID} ${WORKDIR}/bash_profile ${D}/home/${ECW_USER}/.bash_profile
    install -m 0644 -o ${ECW_UID} ${WORKDIR}/bashrc ${D}/home/${ECW_USER}/.bashrc

    install -d -m 0755 -o ${ECW_UID} ${D}/home/${ECW_USER}/bin

}

FILES:${PN} += " \
    /home/${ECW_USER}/.bash_aliases \
    /home/${ECW_USER}/.bash_profile \
    /home/${ECW_USER}/.bashrc \
    /home/${ECW_USER}/bin \
"
