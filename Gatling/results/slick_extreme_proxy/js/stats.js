var stats = {
    type: "GROUP",
name: "Global Information",
path: "",
pathFormatted: "group_missing-name-b06d1",
stats: {
    "name": "Global Information",
    "numberOfRequests": {
        "total": "39064",
        "ok": "24208",
        "ko": "14856"
    },
    "minResponseTime": {
        "total": "1",
        "ok": "6",
        "ko": "1"
    },
    "maxResponseTime": {
        "total": "60017",
        "ok": "58911",
        "ko": "60017"
    },
    "meanResponseTime": {
        "total": "5201",
        "ok": "6362",
        "ko": "3309"
    },
    "standardDeviation": {
        "total": "11312",
        "ok": "10728",
        "ko": "11964"
    },
    "percentiles1": {
        "total": "941",
        "ok": "1702",
        "ko": "607"
    },
    "percentiles2": {
        "total": "2590",
        "ok": "5348",
        "ko": "1052"
    },
    "percentiles3": {
        "total": "33277",
        "ok": "33500",
        "ko": "10193"
    },
    "percentiles4": {
        "total": "60006",
        "ok": "35821",
        "ko": "60014"
    },
    "group1": {
    "name": "t < 800 ms",
    "count": 9348,
    "percentage": 24
},
    "group2": {
    "name": "800 ms < t < 1200 ms",
    "count": 883,
    "percentage": 2
},
    "group3": {
    "name": "t > 1200 ms",
    "count": 13977,
    "percentage": 36
},
    "group4": {
    "name": "failed",
    "count": 14856,
    "percentage": 38
},
    "meanNumberOfRequestsPerSecond": {
        "total": "85.855",
        "ok": "53.204",
        "ko": "32.651"
    }
},
contents: {
"req_database-save-29e5a": {
        type: "REQUEST",
        name: "Database save",
path: "Database save",
pathFormatted: "req_database-save-29e5a",
stats: {
    "name": "Database save",
    "numberOfRequests": {
        "total": "20032",
        "ok": "19131",
        "ko": "901"
    },
    "minResponseTime": {
        "total": "6",
        "ok": "6",
        "ko": "10025"
    },
    "maxResponseTime": {
        "total": "60017",
        "ok": "58911",
        "ko": "60017"
    },
    "meanResponseTime": {
        "total": "9525",
        "ok": "7837",
        "ko": "45373"
    },
    "standardDeviation": {
        "total": "14503",
        "ok": "11619",
        "ko": "21540"
    },
    "percentiles1": {
        "total": "2406",
        "ok": "2250",
        "ko": "60003"
    },
    "percentiles2": {
        "total": "12131",
        "ok": "9168",
        "ko": "60013"
    },
    "percentiles3": {
        "total": "34767",
        "ok": "34083",
        "ko": "60015"
    },
    "percentiles4": {
        "total": "60013",
        "ok": "43277",
        "ko": "60015"
    },
    "group1": {
    "name": "t < 800 ms",
    "count": 6308,
    "percentage": 31
},
    "group2": {
    "name": "800 ms < t < 1200 ms",
    "count": 754,
    "percentage": 4
},
    "group3": {
    "name": "t > 1200 ms",
    "count": 12069,
    "percentage": 60
},
    "group4": {
    "name": "failed",
    "count": 901,
    "percentage": 4
},
    "meanNumberOfRequestsPerSecond": {
        "total": "44.026",
        "ok": "42.046",
        "ko": "1.98"
    }
}
    },"req_database-load-2b4a0": {
        type: "REQUEST",
        name: "Database load",
path: "Database load",
pathFormatted: "req_database-load-2b4a0",
stats: {
    "name": "Database load",
    "numberOfRequests": {
        "total": "19032",
        "ok": "5077",
        "ko": "13955"
    },
    "minResponseTime": {
        "total": "1",
        "ok": "7",
        "ko": "1"
    },
    "maxResponseTime": {
        "total": "34448",
        "ok": "17919",
        "ko": "34448"
    },
    "meanResponseTime": {
        "total": "649",
        "ok": "801",
        "ko": "593"
    },
    "standardDeviation": {
        "total": "926",
        "ok": "989",
        "ko": "896"
    },
    "percentiles1": {
        "total": "423",
        "ok": "19",
        "ko": "491"
    },
    "percentiles2": {
        "total": "1056",
        "ok": "1863",
        "ko": "987"
    },
    "percentiles3": {
        "total": "2090",
        "ok": "2327",
        "ko": "1470"
    },
    "percentiles4": {
        "total": "2502",
        "ok": "2616",
        "ko": "2152"
    },
    "group1": {
    "name": "t < 800 ms",
    "count": 3040,
    "percentage": 16
},
    "group2": {
    "name": "800 ms < t < 1200 ms",
    "count": 129,
    "percentage": 1
},
    "group3": {
    "name": "t > 1200 ms",
    "count": 1908,
    "percentage": 10
},
    "group4": {
    "name": "failed",
    "count": 13955,
    "percentage": 73
},
    "meanNumberOfRequestsPerSecond": {
        "total": "41.829",
        "ok": "11.158",
        "ko": "30.67"
    }
}
    }
}

}

function fillStats(stat){
    $("#numberOfRequests").append(stat.numberOfRequests.total);
    $("#numberOfRequestsOK").append(stat.numberOfRequests.ok);
    $("#numberOfRequestsKO").append(stat.numberOfRequests.ko);

    $("#minResponseTime").append(stat.minResponseTime.total);
    $("#minResponseTimeOK").append(stat.minResponseTime.ok);
    $("#minResponseTimeKO").append(stat.minResponseTime.ko);

    $("#maxResponseTime").append(stat.maxResponseTime.total);
    $("#maxResponseTimeOK").append(stat.maxResponseTime.ok);
    $("#maxResponseTimeKO").append(stat.maxResponseTime.ko);

    $("#meanResponseTime").append(stat.meanResponseTime.total);
    $("#meanResponseTimeOK").append(stat.meanResponseTime.ok);
    $("#meanResponseTimeKO").append(stat.meanResponseTime.ko);

    $("#standardDeviation").append(stat.standardDeviation.total);
    $("#standardDeviationOK").append(stat.standardDeviation.ok);
    $("#standardDeviationKO").append(stat.standardDeviation.ko);

    $("#percentiles1").append(stat.percentiles1.total);
    $("#percentiles1OK").append(stat.percentiles1.ok);
    $("#percentiles1KO").append(stat.percentiles1.ko);

    $("#percentiles2").append(stat.percentiles2.total);
    $("#percentiles2OK").append(stat.percentiles2.ok);
    $("#percentiles2KO").append(stat.percentiles2.ko);

    $("#percentiles3").append(stat.percentiles3.total);
    $("#percentiles3OK").append(stat.percentiles3.ok);
    $("#percentiles3KO").append(stat.percentiles3.ko);

    $("#percentiles4").append(stat.percentiles4.total);
    $("#percentiles4OK").append(stat.percentiles4.ok);
    $("#percentiles4KO").append(stat.percentiles4.ko);

    $("#meanNumberOfRequestsPerSecond").append(stat.meanNumberOfRequestsPerSecond.total);
    $("#meanNumberOfRequestsPerSecondOK").append(stat.meanNumberOfRequestsPerSecond.ok);
    $("#meanNumberOfRequestsPerSecondKO").append(stat.meanNumberOfRequestsPerSecond.ko);
}
