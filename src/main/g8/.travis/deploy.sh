#!/bin/bash

tag=\$1
 
cd \$TRAVIS_BUILD_DIR

export TRAVIS_BUILD_RELEASE_TAG=\${tag}

echo 'BUILD'
