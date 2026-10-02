(function () {
  var urls = window.housePhotos || [];
  var main = document.getElementById("detail-main");
  if (!main || urls.length < 2) return;
  var index = 0;
  var thumbs = document.querySelectorAll("#photo-thumbs button");

  function show(next) {
    index = (next + urls.length) % urls.length;
    main.src = urls[index];
    for (var i = 0; i < thumbs.length; i++) {
      thumbs[i].classList.toggle("is-active", i === index);
    }
  }

  var prev = document.getElementById("photo-prev");
  var next = document.getElementById("photo-next");
  if (prev) prev.addEventListener("click", function () { show(index - 1); });
  if (next) next.addEventListener("click", function () { show(index + 1); });
  for (var i = 0; i < thumbs.length; i++) {
    thumbs[i].addEventListener("click", function () {
      show(Number(this.getAttribute("data-index")));
    });
  }
  document.addEventListener("keydown", function (event) {
    if (event.key === "ArrowLeft") show(index - 1);
    if (event.key === "ArrowRight") show(index + 1);
  });
})();
