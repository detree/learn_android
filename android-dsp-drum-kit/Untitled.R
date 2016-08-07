rm(list=ls())
workdir <- "~/Documents/self/android-dsp-drum-kit"
setwd(workdir)
library(glmnet)
library(MASS)
#================================para====================================
data_file <- "sensor_data.csv"
#================================para end================================
rawd<-read.csv(data_file, sep=',', header=FALSE)
accelX<-rawd[,2]
plot(accelX, type='l')
