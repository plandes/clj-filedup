## makefile automates the build and deployment for lein projects

# type of project, currently one of: clojure, python
PROJ_TYPE=		clojure
PROJ_MODULES=		appassem release
REL_DIST =		$(REL_ZIP) $(REL_BZ2)

# make build dependencies
_ :=	$(shell [ ! -d .git ] && git init ; [ ! -d zenbuild ] && \
	  git submodule add https://github.com/plandes/zenbuild && make gitinit )

include ./zenbuild/main.mk

.PHONY: test
test:
	$(LEIN) test
