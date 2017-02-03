## makefile automates the build and deployment for lein projects

REL_DIST ?=	$(REL_ZIP) $(REL_BZ2)

# location of the http://github.com/plandes/clj-zenbuild cloned directory
ZBHOME ?=	../clj-zenbuild

all:		info

include $(ZBHOME)/src/mk/compile.mk
include $(ZBHOME)/src/mk/dist.mk
include $(ZBHOME)/src/mk/release.mk

.PHONY: test
test:
	$(LEIN) test
