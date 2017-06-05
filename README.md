# WIP - deep-runner

Using deep learning to analyzing your **CSV** data from spreadsheets or other sources needen't require you to know how to write
code. *deep-runner* is a utility that lets you train or run deep-learning networks using a simple declaritive format usng the
[DeepLearning4J](https://deeplearning4j.org/) framework. Several examples have been adapted from the deeplearning4j examples
are provided in `contrib/deeplearning4j` to get you started.

## Using deep-runner

To run deep-runner

    >java deep-runner [options] spec-file.yaml

*Command Line Options*


* --help : display command usage with available options
* --train : when present input data will be used to geneate a model as output
* --data input-file : data may supplied from STDIN or specified as the argument to this option
* --out output-file : data will be sent to STDOUT or specfied as the argument to this option
* --model deep-runner-model
* --debug : when present debug output will be generated

## Setting up deep-runner learning

*deep-runner* uses **YAML** syntax to define your learning networks. **YAML** is simple to learn and use, supporting individual
settings lists and hierarchies, so its got everything needed to setup and train a deep learning network. You can find more
information on **YAML** [here](http://www.yaml.org/start.html)

*A simple example*

### deep-runner settings

### deep-runner network definition

## Building deep-runner

## Extending deep-runner
