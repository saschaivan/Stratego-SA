var stats = {
    type: "GROUP",
name: "Global Information",
path: "",
pathFormatted: "group_missing-name-b06d1",
stats: {
    "name": "Global Information",
    "numberOfRequests": {
        "total": "39064",
        "ok": "25466",
        "ko": "13598"
    },
    "minResponseTime": {
        "total": "1",
        "ok": "2",
        "ko": "1"
    },
    "maxResponseTime": {
        "total": "60017",
        "ok": "59068",
        "ko": "60017"
    },
    "meanResponseTime": {
        "total": "5310",
        "ok": "5094",
        "ko": "5716"
    },
    "standardDeviation": {
        "total": "12647",
        "ok": "10057",
        "ko": "16425"
    },
    "percentiles1": {
        "total": "675",
        "ok": "785",
        "ko": "643"
    },
    "percentiles2": {
        "total": "1982",
        "ok": "3408",
        "ko": "975"
    },
    "percentiles3": {
        "total": "32830",
        "ok": "32522",
        "ko": "60005"
    },
    "percentiles4": {
        "total": "60009",
        "ok": "35545",
        "ko": "60013"
    },
    "group1": {
    "name": "t < 800 ms",
    "count": 12804,
    "percentage": 33
},
    "group2": {
    "name": "800 ms < t < 1200 ms",
    "count": 1800,
    "percentage": 5
},
    "group3": {
    "name": "t > 1200 ms",
    "count": 10862,
    "percentage": 28
},
    "group4": {
    "name": "failed",
    "count": 13598,
    "percentage": 35
},
    "meanNumberOfRequestsPerSecond": {
        "total": "87.002",
        "ok": "56.717",
        "ko": "30.285"
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
        "ok": "18792",
        "ko": "1240"
    },
    "minResponseTime": {
        "total": "2",
        "ok": "2",
        "ko": "14215"
    },
    "maxResponseTime": {
        "total": "60017",
        "ok": "59068",
        "ko": "60017"
    },
    "meanResponseTime": {
        "total": "9761",
        "ok": "6685",
        "ko": "56387"
    },
    "standardDeviation": {
        "total": "16450",
        "ok": "11276",
        "ko": "11290"
    },
    "percentiles1": {
        "total": "1326",
        "ok": "1149",
        "ko": "60005"
    },
    "percentiles2": {
        "total": "11379",
        "ok": "7455",
        "ko": "60010"
    },
    "percentiles3": {
        "total": "60002",
        "ok": "32738",
        "ko": "60014"
    },
    "percentiles4": {
        "total": "60012",
        "ok": "42628",
        "ko": "60016"
    },
    "group1": {
    "name": "t < 800 ms",
    "count": 8453,
    "percentage": 42
},
    "group2": {
    "name": "800 ms < t < 1200 ms",
    "count": 1211,
    "percentage": 6
},
    "group3": {
    "name": "t > 1200 ms",
    "count": 9128,
    "percentage": 46
},
    "group4": {
    "name": "failed",
    "count": 1240,
    "percentage": 6
},
    "meanNumberOfRequestsPerSecond": {
        "total": "44.615",
        "ok": "41.853",
        "ko": "2.762"
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
        "ok": "6674",
        "ko": "12358"
    },
    "minResponseTime": {
        "total": "1",
        "ok": "7",
        "ko": "1"
    },
    "maxResponseTime": {
        "total": "34621",
        "ok": "18660",
        "ko": "34621"
    },
    "meanResponseTime": {
        "total": "625",
        "ok": "614",
        "ko": "631"
    },
    "standardDeviation": {
        "total": "805",
        "ok": "872",
        "ko": "767"
    },
    "percentiles1": {
        "total": "564",
        "ok": "56",
        "ko": "609"
    },
    "percentiles2": {
        "total": "889",
        "ok": "1275",
        "ko": "834"
    },
    "percentiles3": {
        "total": "1824",
        "ok": "2029",
        "ko": "1337"
    },
    "percentiles4": {
        "total": "2455",
        "ok": "2378",
        "ko": "2485"
    },
    "group1": {
    "name": "t < 800 ms",
    "count": 4351,
    "percentage": 23
},
    "group2": {
    "name": "800 ms < t < 1200 ms",
    "count": 589,
    "percentage": 3
},
    "group3": {
    "name": "t > 1200 ms",
    "count": 1734,
    "percentage": 9
},
    "group4": {
    "name": "failed",
    "count": 12358,
    "percentage": 65
},
    "meanNumberOfRequestsPerSecond": {
        "total": "42.388",
        "ok": "14.864",
        "ko": "27.523"
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
