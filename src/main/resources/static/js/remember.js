(function () {
  var form = document.getElementById("login-form");
  if (!form) return;
  var user = form.querySelector("[name=username]");
  var pass = form.querySelector("[name=password]");
  var box = form.querySelector("[name=remember]");
  var key = "house-remember";
  try {
    var saved = JSON.parse(localStorage.getItem(key) || "null");
    if (saved && saved.username) {
      user.value = saved.username;
      pass.value = saved.password || "";
      box.checked = true;
    }
  } catch (e) {}
  form.addEventListener("submit", function () {
    if (box.checked) {
      localStorage.setItem(key, JSON.stringify({
        username: user.value,
        password: pass.value
      }));
    } else {
      localStorage.removeItem(key);
    }
  });
})();
