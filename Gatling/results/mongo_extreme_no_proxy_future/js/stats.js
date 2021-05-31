var stats = {
    type: "GROUP",
name: "Global Information",
path: "",
pathFormatted: "group_missing-name-b06d1",
stats: {
    "name": "Global Information",
    "numberOfRequests": {
        "total": "39064",
        "ok": "38634",
        "ko": "430"
    },
    "minResponseTime": {
        "total": "1",
        "ok": "1",
        "ko": "1"
    },
    "maxResponseTime": {
        "total": "15884",
        "ok": "15884",
        "ko": "56"
    },
    "meanResponseTime": {
        "total": "486",
        "ok": "492",
        "ko": "5"
    },
    "standardDeviation": {
        "total": "1678",
        "ok": "1686",
        "ko": "7"
    },
    "percentiles1": {
        "total": "3",
        "ok": "3",
        "ko": "3"
    },
    "percentiles2": {
        "total": "18",
        "ok": "20",
        "ko": "4"
    },
    "percentiles3": {
        "total": "3103",
        "ok": "3135",
        "ko": "22"
    },
    "percentiles4": {
        "total": "8056",
        "ok": "8060",
        "ko": "38"
    },
    "group1": {
    "name": "t < 800 ms",
    "count": 33984,
    "percentage": 87
},
    "group2": {
    "name": "800 ms < t < 1200 ms",
    "count": 198,
    "percentage": 1
},
    "group3": {
    "name": "t > 1200 ms",
    "count": 4452,
    "percentage": 11
},
    "group4": {
    "name": "failed",
    "count": 430,
    "percentage": 1
},
    "meanNumberOfRequestsPerSecond": {
        "total": "98.896",
        "ok": "97.808",
        "ko": "1.089"
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
        "ok": "20032",
        "ko": "0"
    },
    "minResponseTime": {
        "total": "2",
        "ok": "2",
        "ko": "-"
    },
    "maxResponseTime": {
        "total": "15884",
        "ok": "15884",
        "ko": "-"
    },
    "meanResponseTime": {
        "total": "945",
        "ok": "945",
        "ko": "-"
    },
    "standardDeviation": {
        "total": "2248",
        "ok": "2248",
        "ko": "-"
    },
    "percentiles1": {
        "total": "6",
        "ok": "6",
        "ko": "-"
    },
    "percentiles2": {
        "total": "498",
        "ok": "498",
        "ko": "-"
    },
    "percentiles3": {
        "total": "7174",
        "ok": "7174",
        "ko": "-"
    },
    "percentiles4": {
        "total": "10635",
        "ok": "10635",
        "ko": "-"
    },
    "group1": {
    "name": "t < 800 ms",
    "count": 15382,
    "percentage": 77
},
    "group2": {
    "name": "800 ms < t < 1200 ms",
    "count": 198,
    "percentage": 1
},
    "group3": {
    "name": "t > 1200 ms",
    "count": 4452,
    "percentage": 22
},
    "group4": {
    "name": "failed",
    "count": 0,
    "percentage": 0
},
    "meanNumberOfRequestsPerSecond": {
        "total": "50.714",
        "ok": "50.714",
        "ko": "-"
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
        "ok": "18602",
        "ko": "430"
    },
    "minResponseTime": {
        "total": "1",
        "ok": "1",
        "ko": "1"
    },
    "maxResponseTime": {
        "total": "69",
        "ok": "69",
        "ko": "56"
    },
    "meanResponseTime": {
        "total": "3",
        "ok": "3",
        "ko": "5"
    },
    "standardDeviation": {
        "total": "3",
        "ok": "3",
        "ko": "7"
    },
    "percentiles1": {
        "total": "3",
        "ok": "3",
        "ko": "3"
    },
    "percentiles2": {
        "total": "3",
        "ok": "3",
        "ko": "4"
    },
    "percentiles3": {
        "total": "6",
        "ok": "5",
        "ko": "22"
    },
    "percentiles4": {
        "total": "18",
        "ok": "18",
        "ko": "38"
    },
    "group1": {
    "name": "t < 800 ms",
    "count": 18602,
    "percentage": 98
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
    "count": 430,
    "percentage": 2
},
    "meanNumberOfRequestsPerSecond": {
        "total": "48.182",
        "ok": "47.094",
        "ko": "1.089"
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
