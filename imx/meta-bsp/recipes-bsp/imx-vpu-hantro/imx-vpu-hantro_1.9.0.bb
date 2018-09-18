# Copyright 2017-2018 NXP

require imx-vpu-hantro.inc
LIC_FILES_CHKSUM = "file://COPYING;md5=5ab1a30d0cd181e3408077727ea5a2db"

SRC_URI[md5sum] = "853e866087864c1801dbf4dfab060812"
SRC_URI[sha256sum] = "39cb4cac67a2533a6f62aa1f81b264108375f165bcfd4ca29d3a5345803eed71"

# Compatible only for i.MX with Hantro VPU
COMPATIBLE_MACHINE = "(^$)"
COMPATIBLE_MACHINE_imxvpuhantro = "${MACHINE}"
