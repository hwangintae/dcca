const navSlide = () => {
  const burger = document.querySelector(".burger");
  const nav = document.querySelector(".nav-bar__list");
  const navLinks = document.querySelectorAll(".nav-bar__list-item");

  burger.addEventListener("click", () => {
    //toggle nav
    nav.classList.toggle("nav-active");

    //anmation li
    navLinks.forEach((link, index) => {
      if (link.style.animation) {
        link.style.animation = "";
      } else {
        link.style.animation = `navLinkFade .5s ease forwards ${index / 7 +
          0.5}s`;
      }
    });
    //animation burger
    burger.classList.toggle("toggle");
  });
};
navSlide();
