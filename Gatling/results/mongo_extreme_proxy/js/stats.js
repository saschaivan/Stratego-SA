var stats = {
    type: "GROUP",
name: "Global Information",
path: "",
pathFormatted: "group_missing-name-b06d1",
stats: {
    "name": "Global Information",
    "numberOfRequests": {
        "total": "39064",
        "ok": "36777",
        "ko": "2287"
    },
    "minResponseTime": {
        "total": "1",
        "ok": "1",
        "ko": "1"
    },
    "maxResponseTime": {
        "total": "21328",
        "ok": "21328",
        "ko": "10953"
    },
    "meanResponseTime": {
        "total": "634",
        "ok": "500",
        "ko": "2788"
    },
    "standardDeviation": {
        "total": "2121",
        "ok": "1761",
        "ko": "4695"
    },
    "percentiles1": {
        "total": "3",
        "ok": "3",
        "ko": "4"
    },
    "percentiles2": {
        "total": "18",
        "ok": "17",
        "ko": "10442"
    },
    "percentiles3": {
        "total": "3849",
        "ok": "3433",
        "ko": "10864"
    },
    "percentiles4": {
        "total": "10851",
        "ok": "7731",
        "ko": "10917"
    },
    "group1": {
    "name": "t < 800 ms",
    "count": 32629,
    "percentage": 84
},
    "group2": {
    "name": "800 ms < t < 1200 ms",
    "count": 59,
    "percentage": 0
},
    "group3": {
    "name": "t > 1200 ms",
    "count": 4089,
    "percentage": 10
},
    "group4": {
    "name": "failed",
    "count": 2287,
    "percentage": 6
},
    "meanNumberOfRequestsPerSecond": {
        "total": "96.693",
        "ok": "91.032",
        "ko": "5.661"
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
        "ok": "19437",
        "ko": "595"
    },
    "minResponseTime": {
        "total": "2",
        "ok": "2",
        "ko": "10156"
    },
    "maxResponseTime": {
        "total": "21328",
        "ok": "21328",
        "ko": "10953"
    },
    "meanResponseTime": {
        "total": "1232",
        "ok": "942",
        "ko": "10704"
    },
    "standardDeviation": {
        "total": "2835",
        "ok": "2335",
        "ko": "183"
    },
    "percentiles1": {
        "total": "6",
        "ok": "6",
        "ko": "10790"
    },
    "percentiles2": {
        "total": "452",
        "ok": "307",
        "ko": "10849"
    },
    "percentiles3": {
        "total": "7655",
        "ok": "4762",
        "ko": "10911"
    },
    "percentiles4": {
        "total": "15314",
        "ok": "15333",
        "ko": "10926"
    },
    "group1": {
    "name": "t < 800 ms",
    "count": 15289,
    "percentage": 76
},
    "group2": {
    "name": "800 ms < t < 1200 ms",
    "count": 59,
    "percentage": 0
},
    "group3": {
    "name": "t > 1200 ms",
    "count": 4089,
    "percentage": 20
},
    "group4": {
    "name": "failed",
    "count": 595,
    "percentage": 3
},
    "meanNumberOfRequestsPerSecond": {
        "total": "49.584",
        "ok": "48.111",
        "ko": "1.473"
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
        "ok": "17340",
        "ko": "1692"
    },
    "minResponseTime": {
        "total": "1",
        "ok": "1",
        "ko": "1"
    },
    "maxResponseTime": {
        "total": "106",
        "ok": "106",
        "ko": "86"
    },
    "meanResponseTime": {
        "total": "3",
        "ok": "3",
        "ko": "4"
    },
    "standardDeviation": {
        "total": "3",
        "ok": "3",
        "ko": "4"
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
        "total": "8",
        "ok": "7",
        "ko": "8"
    },
    "percentiles4": {
        "total": "17",
        "ok": "17",
        "ko": "15"
    },
    "group1": {
    "name": "t < 800 ms",
    "count": 17340,
    "percentage": 91
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
    "count": 1692,
    "percentage": 9
},
    "meanNumberOfRequestsPerSecond": {
        "total": "47.109",
        "ok": "42.921",
        "ko": "4.188"
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
