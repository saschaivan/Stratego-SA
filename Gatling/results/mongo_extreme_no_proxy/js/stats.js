var stats = {
    type: "GROUP",
name: "Global Information",
path: "",
pathFormatted: "group_missing-name-b06d1",
stats: {
    "name": "Global Information",
    "numberOfRequests": {
        "total": "39064",
        "ok": "37661",
        "ko": "1403"
    },
    "minResponseTime": {
        "total": "1",
        "ok": "1",
        "ko": "1"
    },
    "maxResponseTime": {
        "total": "17443",
        "ok": "17443",
        "ko": "10945"
    },
    "meanResponseTime": {
        "total": "624",
        "ok": "474",
        "ko": "4654"
    },
    "standardDeviation": {
        "total": "2073",
        "ok": "1668",
        "ko": "5312"
    },
    "percentiles1": {
        "total": "3",
        "ok": "3",
        "ko": "5"
    },
    "percentiles2": {
        "total": "15",
        "ok": "8",
        "ko": "10801"
    },
    "percentiles3": {
        "total": "3810",
        "ok": "3233",
        "ko": "10897"
    },
    "percentiles4": {
        "total": "10857",
        "ok": "7665",
        "ko": "10931"
    },
    "group1": {
    "name": "t < 800 ms",
    "count": 33464,
    "percentage": 86
},
    "group2": {
    "name": "800 ms < t < 1200 ms",
    "count": 46,
    "percentage": 0
},
    "group3": {
    "name": "t > 1200 ms",
    "count": 4151,
    "percentage": 11
},
    "group4": {
    "name": "failed",
    "count": 1403,
    "percentage": 4
},
    "meanNumberOfRequestsPerSecond": {
        "total": "97.66",
        "ok": "94.153",
        "ko": "3.507"
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
        "ok": "19423",
        "ko": "609"
    },
    "minResponseTime": {
        "total": "2",
        "ok": "2",
        "ko": "10065"
    },
    "maxResponseTime": {
        "total": "17443",
        "ok": "17443",
        "ko": "10945"
    },
    "meanResponseTime": {
        "total": "1214",
        "ok": "916",
        "ko": "10718"
    },
    "standardDeviation": {
        "total": "2769",
        "ok": "2233",
        "ko": "195"
    },
    "percentiles1": {
        "total": "6",
        "ok": "5",
        "ko": "10822"
    },
    "percentiles2": {
        "total": "498",
        "ok": "307",
        "ko": "10870"
    },
    "percentiles3": {
        "total": "7516",
        "ok": "4574",
        "ko": "10919"
    },
    "percentiles4": {
        "total": "14918",
        "ok": "15023",
        "ko": "10938"
    },
    "group1": {
    "name": "t < 800 ms",
    "count": 15226,
    "percentage": 76
},
    "group2": {
    "name": "800 ms < t < 1200 ms",
    "count": 46,
    "percentage": 0
},
    "group3": {
    "name": "t > 1200 ms",
    "count": 4151,
    "percentage": 21
},
    "group4": {
    "name": "failed",
    "count": 609,
    "percentage": 3
},
    "meanNumberOfRequestsPerSecond": {
        "total": "50.08",
        "ok": "48.557",
        "ko": "1.522"
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
        "ok": "18238",
        "ko": "794"
    },
    "minResponseTime": {
        "total": "1",
        "ok": "1",
        "ko": "1"
    },
    "maxResponseTime": {
        "total": "92",
        "ok": "92",
        "ko": "24"
    },
    "meanResponseTime": {
        "total": "3",
        "ok": "3",
        "ko": "3"
    },
    "standardDeviation": {
        "total": "3",
        "ok": "3",
        "ko": "2"
    },
    "percentiles1": {
        "total": "2",
        "ok": "2",
        "ko": "3"
    },
    "percentiles2": {
        "total": "3",
        "ok": "3",
        "ko": "3"
    },
    "percentiles3": {
        "total": "5",
        "ok": "4",
        "ko": "7"
    },
    "percentiles4": {
        "total": "15",
        "ok": "15",
        "ko": "13"
    },
    "group1": {
    "name": "t < 800 ms",
    "count": 18238,
    "percentage": 96
},
    "group2": {
    "name": "800 ms < t < 1200 ms",
    "count": 0,
    "percentage": 0
},
    "group3": {
    "name": "t > 1200 ms",
    "count": 0,
    "percentage": 0
},
    "group4": {
    "name": "failed",
    "count": 794,
    "percentage": 4
},
    "meanNumberOfRequestsPerSecond": {
        "total": "47.58",
        "ok": "45.595",
        "ko": "1.985"
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
