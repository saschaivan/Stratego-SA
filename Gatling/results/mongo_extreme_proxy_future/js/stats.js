var stats = {
    type: "GROUP",
name: "Global Information",
path: "",
pathFormatted: "group_missing-name-b06d1",
stats: {
    "name": "Global Information",
    "numberOfRequests": {
        "total": "39064",
        "ok": "38829",
        "ko": "235"
    },
    "minResponseTime": {
        "total": "1",
        "ok": "1",
        "ko": "1"
    },
    "maxResponseTime": {
        "total": "17495",
        "ok": "17495",
        "ko": "18"
    },
    "meanResponseTime": {
        "total": "453",
        "ok": "455",
        "ko": "4"
    },
    "standardDeviation": {
        "total": "1566",
        "ok": "1570",
        "ko": "3"
    },
    "percentiles1": {
        "total": "3",
        "ok": "3",
        "ko": "3"
    },
    "percentiles2": {
        "total": "17",
        "ok": "19",
        "ko": "4"
    },
    "percentiles3": {
        "total": "2651",
        "ok": "2670",
        "ko": "10"
    },
    "percentiles4": {
        "total": "7492",
        "ok": "7495",
        "ko": "15"
    },
    "group1": {
    "name": "t < 800 ms",
    "count": 34145,
    "percentage": 87
},
    "group2": {
    "name": "800 ms < t < 1200 ms",
    "count": 306,
    "percentage": 1
},
    "group3": {
    "name": "t > 1200 ms",
    "count": 4378,
    "percentage": 11
},
    "group4": {
    "name": "failed",
    "count": 235,
    "percentage": 1
},
    "meanNumberOfRequestsPerSecond": {
        "total": "99.653",
        "ok": "99.054",
        "ko": "0.599"
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
        "total": "17495",
        "ok": "17495",
        "ko": "-"
    },
    "meanResponseTime": {
        "total": "880",
        "ok": "880",
        "ko": "-"
    },
    "standardDeviation": {
        "total": "2099",
        "ok": "2099",
        "ko": "-"
    },
    "percentiles1": {
        "total": "6",
        "ok": "6",
        "ko": "-"
    },
    "percentiles2": {
        "total": "499",
        "ok": "499",
        "ko": "-"
    },
    "percentiles3": {
        "total": "3907",
        "ok": "3907",
        "ko": "-"
    },
    "percentiles4": {
        "total": "10103",
        "ok": "10103",
        "ko": "-"
    },
    "group1": {
    "name": "t < 800 ms",
    "count": 15348,
    "percentage": 77
},
    "group2": {
    "name": "800 ms < t < 1200 ms",
    "count": 306,
    "percentage": 2
},
    "group3": {
    "name": "t > 1200 ms",
    "count": 4378,
    "percentage": 22
},
    "group4": {
    "name": "failed",
    "count": 0,
    "percentage": 0
},
    "meanNumberOfRequestsPerSecond": {
        "total": "51.102",
        "ok": "51.102",
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
        "ok": "18797",
        "ko": "235"
    },
    "minResponseTime": {
        "total": "1",
        "ok": "1",
        "ko": "1"
    },
    "maxResponseTime": {
        "total": "68",
        "ok": "68",
        "ko": "18"
    },
    "meanResponseTime": {
        "total": "3",
        "ok": "3",
        "ko": "4"
    },
    "standardDeviation": {
        "total": "3",
        "ok": "3",
        "ko": "3"
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
        "total": "5",
        "ok": "5",
        "ko": "10"
    },
    "percentiles4": {
        "total": "17",
        "ok": "17",
        "ko": "15"
    },
    "group1": {
    "name": "t < 800 ms",
    "count": 18797,
    "percentage": 99
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
    "count": 235,
    "percentage": 1
},
    "meanNumberOfRequestsPerSecond": {
        "total": "48.551",
        "ok": "47.952",
        "ko": "0.599"
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
