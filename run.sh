#!/bin/bash
echo "Hello World"
wget -O samtools.tar.gz "https://github.com/samtools/samtools/releases/download/1.9/samtools-1.9.tar.bz2" && tar -xvf samtools.tar.gz && cd samtools-1.9/
./configure CC="gcc -static" --without-curses
echo "configure ran --------------------"
make
echo "make ran --------------------"
