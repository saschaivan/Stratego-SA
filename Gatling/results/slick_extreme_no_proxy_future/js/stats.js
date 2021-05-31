var stats = {
    type: "GROUP",
name: "Global Information",
path: "",
pathFormatted: "group_missing-name-b06d1",
stats: {
    "name": "Global Information",
    "numberOfRequests": {
        "total": "39064",
        "ok": "24172",
        "ko": "14892"
    },
    "minResponseTime": {
        "total": "1",
        "ok": "2",
        "ko": "1"
    },
    "maxResponseTime": {
        "total": "60016",
        "ok": "59295",
        "ko": "60016"
    },
    "meanResponseTime": {
        "total": "5132",
        "ok": "5347",
        "ko": "4781"
    },
    "standardDeviation": {
        "total": "12249",
        "ok": "10184",
        "ko": "15000"
    },
    "percentiles1": {
        "total": "666",
        "ok": "764",
        "ko": "643"
    },
    "percentiles2": {
        "total": "1854",
        "ok": "3730",
        "ko": "931"
    },
    "percentiles3": {
        "total": "32885",
        "ok": "32684",
        "ko": "60005"
    },
    "percentiles4": {
        "total": "60009",
        "ok": "34150",
        "ko": "60014"
    },
    "group1": {
    "name": "t < 800 ms",
    "count": 12187,
    "percentage": 31
},
    "group2": {
    "name": "800 ms < t < 1200 ms",
    "count": 1413,
    "percentage": 4
},
    "group3": {
    "name": "t > 1200 ms",
    "count": 10572,
    "percentage": 27
},
    "group4": {
    "name": "failed",
    "count": 14892,
    "percentage": 38
},
    "meanNumberOfRequestsPerSecond": {
        "total": "87.196",
        "ok": "53.955",
        "ko": "33.241"
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
        "ok": "18892",
        "ko": "1140"
    },
    "minResponseTime": {
        "total": "2",
        "ok": "2",
        "ko": "13116"
    },
    "maxResponseTime": {
        "total": "60016",
        "ok": "59295",
        "ko": "60016"
    },
    "meanResponseTime": {
        "total": "9462",
        "ok": "6691",
        "ko": "55383"
    },
    "standardDeviation": {
        "total": "15929",
        "ok": "11146",
        "ko": "12788"
    },
    "percentiles1": {
        "total": "1380",
        "ok": "1180",
        "ko": "60008"
    },
    "percentiles2": {
        "total": "9600",
        "ok": "7574",
        "ko": "60011"
    },
    "percentiles3": {
        "total": "59223",
        "ok": "32888",
        "ko": "60014"
    },
    "percentiles4": {
        "total": "60013",
        "ok": "34645",
        "ko": "60016"
    },
    "group1": {
    "name": "t < 800 ms",
    "count": 8574,
    "percentage": 43
},
    "group2": {
    "name": "800 ms < t < 1200 ms",
    "count": 981,
    "percentage": 5
},
    "group3": {
    "name": "t > 1200 ms",
    "count": 9337,
    "percentage": 47
},
    "group4": {
    "name": "failed",
    "count": 1140,
    "percentage": 6
},
    "meanNumberOfRequestsPerSecond": {
        "total": "44.714",
        "ok": "42.17",
        "ko": "2.545"
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
        "ok": "5280",
        "ko": "13752"
    },
    "minResponseTime": {
        "total": "1",
        "ok": "6",
        "ko": "1"
    },
    "maxResponseTime": {
        "total": "28164",
        "ok": "28164",
        "ko": "15566"
    },
    "meanResponseTime": {
        "total": "573",
        "ok": "539",
        "ko": "586"
    },
    "standardDeviation": {
        "total": "607",
        "ok": "846",
        "ko": "485"
    },
    "percentiles1": {
        "total": "552",
        "ok": "15",
        "ko": "609"
    },
    "percentiles2": {
        "total": "878",
        "ok": "1131",
        "ko": "848"
    },
    "percentiles3": {
        "total": "1606",
        "ok": "2003",
        "ko": "1225"
    },
    "percentiles4": {
        "total": "2116",
        "ok": "2252",
        "ko": "1782"
    },
    "group1": {
    "name": "t < 800 ms",
    "count": 3613,
    "percentage": 19
},
    "group2": {
    "name": "800 ms < t < 1200 ms",
    "count": 432,
    "percentage": 2
},
    "group3": {
    "name": "t > 1200 ms",
    "count": 1235,
    "percentage": 6
},
    "group4": {
    "name": "failed",
    "count": 13752,
    "percentage": 72
},
    "meanNumberOfRequestsPerSecond": {
        "total": "42.482",
        "ok": "11.786",
        "ko": "30.696"
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
