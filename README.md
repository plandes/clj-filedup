# Find Duplicate Files by Content

[![Travis CI Build Status][travis-badge]][travis-link]

  [travis-link]: https://travis-ci.org/plandes/clj-filedup
  [travis-badge]: https://travis-ci.org/plandes/clj-filedup.svg?branch=master

This is a command line program that finds files that are duplicates in a
directory tree.  It traverses a directory recursively and finds files that are
the same by content.  It does this by computing and reporting
duplicate [md5sums](https://en.wikipedia.org/wiki/Md5sum).


## Obtaining

The latest release binaries are
available [here](https://github.com/plandes/clj-filedup/releases/latest).


## Usage

This is a command line application that has the following usage (given with `-h`):
```sql
$ filedup -h
usage: filedup [options]
find duplicate files
  -l, --level <log level>      INFO  Log level to set in the Log4J2 system.
  -a, --absolute                     Print absolute path names if given
  -d, --directory <directory>  .     The directory to find duplicate files.
```

To find all the duplicates in directory `test-directory`:
```bash
$ filedup -d test-resources
test-resources/dupdir/file1 test-resources/dupdir/file3
```

To get more output:
```bash
$ filedup -l info -d test-resources
filedup: find-dups: finding duplicate files in test-resources
filedup: find-dups: processing test-resources/dupdir/file1
filedup: find-dups: processing test-resources/dupdir/file2
filedup: find-dups: processing test-resources/dupdir/file3
filedup: find-dups: processing test-resources/log4j2.xml
test-resources/dupdir/file1 test-resources/dupdir/file3
```


## Building

To build from source, do the folling:

- Install [Leiningen](http://leiningen.org) (this is just a script)
- Install [GNU make](https://www.gnu.org/software/make/)
- Install [Git](https://git-scm.com)
- Download the source: `git clone https://github.com/clj-nlp-feature && cd clj-nlp-feature`
- Download the make include files:
```bash
mkdir ../clj-zenbuild && wget -O - https://api.github.com/repos/plandes/clj-zenbuild/tarball | tar zxfv - -C ../clj-zenbuild --strip-components 1
```
- Compile: `make compile` do compile or `make install` to install in your local
  maven repo.


## Use as a Clojure Library

This software is written in Clojure and can be used in your own Clojure (or
Java) program.  For example, you could create
a [Leiningen plugin](https://nakkaya.com/2010/02/25/writing-leiningen-plugins-101/).


### Obtaining

In your `project.clj` file, add:

[![Clojars Project](https://clojars.org/com.zensols.tools/filedup/latest-version.svg)](https://clojars.org/com.zensols.tools/filedup/)


### Documentation

API [documentation](https://plandes.github.io/clj-filedup/codox/index.html).


## Changelog

An extensive changelog is available [here](CHANGELOG.md).


## License

Copyright © 2017 Paul Landes

Apache License version 2.0

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

[http://www.apache.org/licenses/LICENSE-2.0](http://www.apache.org/licenses/LICENSE-2.0)

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
