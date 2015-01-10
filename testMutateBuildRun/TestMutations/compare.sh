#!/bin/sh

if diff $1 $2 >/dev/null ; then
  echo Same
else
  echo Different
fi