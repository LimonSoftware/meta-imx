# Copyright (C) 2019-2024 NXP

SUMMARY = "Adaptation layer between v2xCryptoLibrary SE API and HSM API"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=664939843ae3416d811479b21978e8b4"

DEPENDS = "imx-secure-enclave-seco"

SRC_URI = "${V2XSEHSM_SRC};branch=${SRCBRANCH} \
    file://0001-LF-12809-Use-standard-linux-build-assumptions.patch"
V2XSEHSM_SRC ?= "git://github.com/nxp-imx/v2xsehsm.git;protocol=https"
SRCBRANCH = "main"
SRCREV = "0e7ecffc0bca7f5e280e9cf69c71eebf6ec3b142"

S = "${WORKDIR}/git"

inherit cmake

PACKAGECONFIG ?= ""
PACKAGECONFIG[lttng] = "-DTRACE_SETTING=-DENABLE_LTTNG,,lttng-ust"

EXTRA_OECMAKE += "-DSECO_LIBS_DIR=${STAGING_DIR_TARGET}${libdir}"
CFLAGS += "-I${STAGING_INCDIR} -I${STAGING_INCDIR}/hsm"
LDFLAGS += "-L${STAGING_LIBDIR}"

COMPATIBLE_MACHINE = "(mx8dxl-nxp-bsp)"
