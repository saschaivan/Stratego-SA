FROM hseeberger/scala-sbt:8u222_1.3.5_2.13.1
EXPOSE 8080 8080
WORKDIR /stratego
ADD . /stratego
CMD sbt run