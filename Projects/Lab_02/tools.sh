#!/bin/bash
cd $(dirname $0)
cd src
java -cp ../tools/jflex/JFlex.jar JFlex.Main -d scanner scanner/scanner.jflex
