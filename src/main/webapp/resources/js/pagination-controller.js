var renderPrevious = function (currentPage, action) {
    if (currentPage > 1) {
        var link = $("<a class='page-link' href='#'> Previous </a>");
        link.on("click", {pageNum: currentPage - 1}, action);
        return $("<li></li>").addClass("page-item").append(link);
    } else {
        return null;
    }
};

var renderNext = function (currentPage, lastPage, action) {
    if (currentPage < lastPage) {
        var link = $("<a class='page-link' href='#'> Next </a>");
        link.on("click", {pageNum: currentPage + 1}, action);
        return $("<li></li>").addClass("page-item").append(link);
    } else {
        return null;
    }
};

var renderPageEntry = function (index, currentPage, action) {
    if (index === currentPage) {
        return "<li class='page-item active'><a class='page-link'>" + index + "</a></li>";
    } else {
        var link = $("<a class='page-link' href='#'>"
            + index +
            "</a>");
        link.on("click", {pageNum: index}, action);
        return $("<li></li>").addClass("page-item").append(link);
    }
};

var renderTruncation = function () {
    var link = $("<a class='page-link disabled'>&hellip;</a>");
    return $("<li></li>").addClass("page-item").append(link);
};

function pagination(currentPage, lastPage, action) {

    var paginator = $("<ul></ul>").addClass("pagination");

    paginator.append(renderPrevious(currentPage, action));

    var left = Math.max(currentPage - 2, 1);
    var right = Math.min(currentPage + 2, lastPage);

    if (left > 1) {
        paginator.append(renderTruncation());
    }

    for (var i = left; i < right + 1; i++) {
        paginator.append(renderPageEntry(i, currentPage, action));
    }

    if (right < lastPage) {
        paginator.append(renderTruncation());
    }

    paginator.append(renderNext(currentPage, lastPage, action));

    return paginator;
}