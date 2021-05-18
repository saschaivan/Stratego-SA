var stats = {
    type: "GROUP",
name: "Global Information",
path: "",
pathFormatted: "group_missing-name-b06d1",
stats: {
    "name": "Global Information",
    "numberOfRequests": {
        "total": "39064",
        "ok": "23560",
        "ko": "15504"
    },
    "minResponseTime": {
        "total": "1",
        "ok": "6",
        "ko": "1"
    },
    "maxResponseTime": {
        "total": "60018",
        "ok": "59497",
        "ko": "60018"
    },
    "meanResponseTime": {
        "total": "5927",
        "ok": "6387",
        "ko": "5229"
    },
    "standardDeviation": {
        "total": "12901",
        "ok": "11019",
        "ko": "15298"
    },
    "percentiles1": {
        "total": "1106",
        "ok": "1786",
        "ko": "789"
    },
    "percentiles2": {
        "total": "3043",
        "ok": "5438",
        "ko": "1303"
    },
    "percentiles3": {
        "total": "34663",
        "ok": "34116",
        "ko": "60004"
    },
    "percentiles4": {
        "total": "60007",
        "ok": "44127",
        "ko": "60013"
    },
    "group1": {
    "name": "t < 800 ms",
    "count": 8992,
    "percentage": 23
},
    "group2": {
    "name": "800 ms < t < 1200 ms",
    "count": 854,
    "percentage": 2
},
    "group3": {
    "name": "t > 1200 ms",
    "count": 13714,
    "percentage": 35
},
    "group4": {
    "name": "failed",
    "count": 15504,
    "percentage": 40
},
    "meanNumberOfRequestsPerSecond": {
        "total": "84.554",
        "ok": "50.996",
        "ko": "33.558"
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
        "ok": "18584",
        "ko": "1448"
    },
    "minResponseTime": {
        "total": "6",
        "ok": "6",
        "ko": "10124"
    },
    "maxResponseTime": {
        "total": "60018",
        "ok": "59497",
        "ko": "60018"
    },
    "meanResponseTime": {
        "total": "10787",
        "ok": "7826",
        "ko": "48793"
    },
    "standardDeviation": {
        "total": "16568",
        "ok": "11977",
        "ko": "19988"
    },
    "percentiles1": {
        "total": "2563",
        "ok": "2256",
        "ko": "60005"
    },
    "percentiles2": {
        "total": "15275",
        "ok": "8987",
        "ko": "60008"
    },
    "percentiles3": {
        "total": "60002",
        "ok": "34468",
        "ko": "60014"
    },
    "percentiles4": {
        "total": "60012",
        "ok": "54029",
        "ko": "60015"
    },
    "group1": {
    "name": "t < 800 ms",
    "count": 6147,
    "percentage": 31
},
    "group2": {
    "name": "800 ms < t < 1200 ms",
    "count": 723,
    "percentage": 4
},
    "group3": {
    "name": "t > 1200 ms",
    "count": 11714,
    "percentage": 58
},
    "group4": {
    "name": "failed",
    "count": 1448,
    "percentage": 7
},
    "meanNumberOfRequestsPerSecond": {
        "total": "43.359",
        "ok": "40.225",
        "ko": "3.134"
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
        "ok": "4976",
        "ko": "14056"
    },
    "minResponseTime": {
        "total": "1",
        "ok": "7",
        "ko": "1"
    },
    "maxResponseTime": {
        "total": "34700",
        "ok": "34700",
        "ko": "34021"
    },
    "meanResponseTime": {
        "total": "813",
        "ok": "1015",
        "ko": "741"
    },
    "standardDeviation": {
        "total": "1291",
        "ok": "1592",
        "ko": "1158"
    },
    "percentiles1": {
        "total": "580",
        "ok": "26",
        "ko": "643"
    },
    "percentiles2": {
        "total": "1250",
        "ok": "1998",
        "ko": "1154"
    },
    "percentiles3": {
        "total": "2412",
        "ok": "3205",
        "ko": "1951"
    },
    "percentiles4": {
        "total": "3513",
        "ok": "3663",
        "ko": "2666"
    },
    "group1": {
    "name": "t < 800 ms",
    "count": 2845,
    "percentage": 15
},
    "group2": {
    "name": "800 ms < t < 1200 ms",
    "count": 131,
    "percentage": 1
},
    "group3": {
    "name": "t > 1200 ms",
    "count": 2000,
    "percentage": 11
},
    "group4": {
    "name": "failed",
    "count": 14056,
    "percentage": 74
},
    "meanNumberOfRequestsPerSecond": {
        "total": "41.195",
        "ok": "10.771",
        "ko": "30.424"
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
