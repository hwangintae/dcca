const slides = document.querySelector(".slider").children;
const prev = document.querySelector(".prev");
const next = document.querySelector(".next");
const indicator = document.querySelector(".indicator");

let index = 0;

prev.addEventListener("click", function() {
  prevSlide();
  updateCircleIndicator();
  //   console.log("click");
  //   클릭되는지 console에서 log를 띄우는 것
});

next.addEventListener("click", function() {
  nextSlide();
  updateCircleIndicator();
});

function prevSlide() {
  if (index == 0) {
    index = slides.length - 1;
  } else {
    index--;
  }
  changeSlide();
}

function nextSlide() {
  if (index == slides.length - 1) {
    index = 0;
  } else {
    index++;
  }
  //   console.log(index);
  // 슬라이드 넘어갈때 index값 확인
  changeSlide();
}

function changeSlide() {
  for (let i = 0; i < slides.length; i++) {
    slides[i].classList.remove("active");
  }
  slides[index].classList.add("active");
}

// 이미지 아래 원 생성
function circleIndicator() {
  for (let i = 0; i < slides.length; i++) {
    const div = document.createElement("div");
    div.innerHTML = i + 1;
    div.id = i;
    div.setAttribute("onclick", "indicateSlide(this)");
    if (i == 0) {
      div.className = "active";
    }
    indicator.appendChild(div);
  }
}
circleIndicator();

// 이미지에 따라 원 색상 변경
function updateCircleIndicator() {
  for (let i = 0; i < indicator.children.length; i++) {
    indicator.children[i].classList.remove("active");
  }
  indicator.children[index].classList.add("active");
}

function indicateSlide(element) {
  index = element.id;
  changeSlide();
  updateCircleIndicator();
}

// 자동 이미지 슬라이드
function autoPlay() {
  nextSlide();
  updateCircleIndicator();
}

let timer = setInterval(autoPlay, 4000);

function reseTimer() {
  clearInterval(timer);
  timer = setInterval(autoPlay, 4000);
}
