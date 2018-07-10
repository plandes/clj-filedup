# Find Duplicate Files by Content

[![Travis CI Build Status][travis-badge]][travis-link]

This is a command line program that finds files that are duplicates in a
directory tree.  It traverses a directory recursively and finds files that are
the same by content.  It does this by computing and reporting
duplicate [md5sums](https://en.wikipedia.org/wiki/Md5sum).

<!-- markdown-toc start - Don't edit this section. Run M-x markdown-toc-refresh-toc -->
## Table of Contents

- [Obtaining](#obtaining)
- [Usage](#usage)
- [Building](#building)
- [Use as a Clojure Library](#use-as-a-clojure-library)
    - [Documentation](#documentation)
- [Changelog](#changelog)
- [License](#license)

<!-- markdown-toc end -->


## Obtaining

The latest release binaries are
available [here](https://github.com/plandes/clj-filedup/releases/latest).


## Usage

[Java] is needed to run this program.

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
- Download the source: `git clone --recurse-submodules https://github.com/plandes/clj-nlp-parse && cd clj-nlp-parse`
- Build the software: `make jar`
- Build the distribution binaries: `make dist`

Note that you can also build a single jar file with all the dependencies with: `make uber`


### Documentation

API [documentation](https://plandes.github.io/clj-filedup/codox/index.html).

The command line documentation is given with the `--help` option.


## Changelog

An extensive changelog is available [here](CHANGELOG.md).


## License

Copyright (c) 2016, 2017, 2018 Paul Landes

Permission is hereby granted, free of charge, to any person obtaining a copy of
this software and associated documentation files (the "Software"), to deal in
the Software without restriction, including without limitation the rights to
use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies
of the Software, and to permit persons to whom the Software is furnished to do
so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.


<!-- links -->
[Java]: https://java.com/en/download/
[travis-link]: https://travis-ci.org/plandes/clj-filedup
[travis-badge]: https://travis-ci.org/plandes/clj-filedup.svg?branch=master
