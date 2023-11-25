const url = "http://localhost:8081/MyFiview";
const index = "http://localhost:8081/MyFiview/";

// 绑定的事件类型、绑定对象、提示出现位置、提示信息、提示条件
function bindEventTip(events, bindDiv, tipDiv, tipInfo, condition) {
    // class="a" bindDiv/tipDiv = .a   or  input[name="email"]
    var bindDiv = document.querySelector(bindDiv)
    var tipDiv = document.querySelector(tipDiv)

    bindDiv.addEventListener(events, function () {
        if (condition(bindDiv.value)) {
            // 展示提示信息
            tipDiv.style.display = 'block'
            tipDiv.textContent = tipInfo
            console.log(bindDiv.value)
        } else {
            // 隐藏提示信息
            tipDiv.style.display = 'none'
        }

    })
}

function bindEventTipAndRequest(events, bindDiv, tipDiv, tipInfo, validateFunction, requestFunction) {
    var bindDiv = document.querySelector(bindDiv);
    var tipDiv = document.querySelector(tipDiv);
  
    bindDiv.addEventListener(events, function() {
      var value = bindDiv.value;
      if (validateFunction(value)) {
        // 展示提示信息
        tipDiv.style.display = 'block';
        tipDiv.textContent = tipInfo;
      } else {
        // 隐藏提示信息
        tipDiv.style.display = 'none';
        requestFunction(value);
      }
    });
}

export {
    url,
    index,
    bindEventTip,
    bindEventTipAndRequest
};