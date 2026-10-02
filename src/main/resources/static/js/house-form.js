(function () {
  var status = document.getElementById("status");
  if (!status) return;
  var rent = document.getElementById("rent-fields");
  var sale = document.getElementById("sale-fields");
  function sync() {
    var value = status.value;
    if (rent) rent.classList.toggle("is-hidden", value !== "出租中");
    if (sale) sale.classList.toggle("is-hidden", value !== "售卖中");
  }
  status.addEventListener("change", sync);
  sync();
})();
