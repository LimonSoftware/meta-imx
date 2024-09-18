# Copyright (C) 2020-2024 NXP
# Released under the MIT license (see COPYING.MIT for the terms)
# The recipe is licensed under MIT (see COPYING.MIT for the terms)

DESCRIPTION = "Sound Open Firmware with Zephyr"
HOMEPAGE = "https://www.sofproject.org"
SECTION = "kernel"
LICENSE = "Apache-2.0 & BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=fa818a259cbed7ce8bc2a22d35a464fc \
                    file://LICENCE-sof;md5=14abb55d71048ebecff1a104640546b6 \
"
SRC_URI = "${FSL_MIRROR}/${BPN}-${PV}.tar.gz"
SRC_URI[sha256sum] = "b9abccfc9757c9df8d3fae2ac4bda5d5131f9881eb1a939133a5f7d0775a2b36"

inherit allarch

do_install() {
    # Install firmware image 
    install -d ${D}${nonarch_base_libdir}/firmware/imx/sof-zephyr-gcc
    install -d ${D}${nonarch_base_libdir}/firmware/imx/sof-zephyr-xcc
    cp -r sof-zephyr-gcc/* ${D}${nonarch_base_libdir}/firmware/imx/sof-zephyr-gcc
    cp -r sof-zephyr-xcc/* ${D}${nonarch_base_libdir}/firmware/imx/sof-zephyr-xcc
    cp -r sof-tplg ${D}${nonarch_base_libdir}/firmware/imx/
    # Copy symbolic link
    cp -P sof  ${D}${nonarch_base_libdir}/firmware/imx/
}

FILES:${PN} = "${nonarch_base_libdir}/firmware/imx/sof*"
