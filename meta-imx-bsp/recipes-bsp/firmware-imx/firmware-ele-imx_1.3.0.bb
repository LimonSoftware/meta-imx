# Copyright 2021-2024 NXP
SUMMARY = "NXP i.MX ELE firmware"
DESCRIPTION = "EdgeLock Secure Enclave firmware for i.MX series SoCs"
SECTION = "base"
LICENSE = "Proprietary"
LIC_FILES_CHKSUM = "file://COPYING;md5=ca53281cc0caa7e320d4945a896fb837"

inherit fsl-eula-unpack use-imx-security-controller-firmware deploy

SRC_URI = "${FSL_MIRROR}/${BP}-${IMX_SRCREV_ABBREV}.bin;fsl-eula=true"
IMX_SRCREV_ABBREV = "bb26a75"
SRC_URI[sha256sum] = "592272fedf1371e50e64609236f48a632b128280d47f7189fc9dc5244e5d5c3d"

S = "${WORKDIR}/${BP}-${IMX_SRCREV_ABBREV}"

do_compile[noexec] = "1"

do_install() {
   install -d ${D}${nonarch_base_libdir}/firmware/imx/ele
   install -m 0644 ${S}/${SECO_FIRMWARE_NAME} ${D}${nonarch_base_libdir}/firmware/imx/ele
    if [ -e ${S}/${SECOEXT_FIRMWARE_NAME} ]; then
        install -m 0644 ${S}/${SECOEXT_FIRMWARE_NAME} ${D}${nonarch_base_libdir}/firmware/imx/ele
    fi
}

do_deploy () {
    # Deploy the related firmware to be package by imx-boot
    install -m 0644 ${S}/${SECO_FIRMWARE_NAME}  ${DEPLOYDIR}
}
addtask deploy after do_install before do_build

PACKAGES += "${PN}-ext"

ALLOW_EMPTY:${PN}-ext = "1"

FILES:${PN} += "${nonarch_base_libdir}/firmware/imx/ele/${SECO_FIRMWARE_NAME}"
FILES:${PN}-ext += "${nonarch_base_libdir}/firmware/imx/ele/${SECOEXT_FIRMWARE_NAME}"

COMPATIBLE_MACHINE = "(mx8ulp-nxp-bsp|mx9-nxp-bsp)"
