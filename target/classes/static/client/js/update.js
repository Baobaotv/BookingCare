
// update thong tin 
const inputinfo = document.querySelector('.checkkk')
const updateinfo = document.querySelector('.update')

/*inputinfo.addEventListener('click', () => {
    if (this.checked) {
        updateinfo.classList.toggle('onn')
    }
    else {
        updateinfo.classList.toggle('onn')
    }
})*/
var tong=$('.form-body');
var old=$('.form-body').html()+"";

function myFunction() {
	if($('#checkPass').prop('checked')){
		 $('.update').css("display", "block");
		 $("#btnUpdate").remove();
		var insert='<div class="update"><div class="form-group"><input id="password" name="password" type="password" placeholder="Nhập mật khẩu mới " class="form-control">';
		 insert=insert+'<span class="form-message"></span></div><div class="form-group">';
		 insert=insert+'<input id="confirmPassword" name="confirmPassword" type="password" placeholder="Xác nhận mật khẩu" class="form-control">';
		 insert=insert+'<span class="form-message"></span></div></div><button class="form-submit" id="btnUpdate">UpDate</button>';
		 var test = document.querySelector('.form-body');
		 test.innerHTML= test.innerHTML+insert;
		 $('.update').css("display", "block");
		 $("#checkPass").prop('checked',true);
	}else{
		var test = document.querySelector('.form-body');
		 test.innerHTML= old;
		 b=$("#checkPass");
	}
	};
	
	$('#formSignup').validate({
		rules: {
			fullName: {
				required: true
				
			},
			email: {
				required: true,
				email: true
			},
			phone: {
				required: true,
				number:true,
                minlength:10,
                maxlength:10
			},
			password: {
				required: true
			},
			confirmPassword: {
				equalTo: "#password"
			}
		},
		messages: {
			fullName: {
				required: "Tên không được để trống"
			},
			email: {
				required: "Mail không được để trống"
			},
			phone: {
				required: "SDT không được để trống"
			},
			email: {
				required: "Sai định dạng email"
			},
			password: {
				required: "Password không được để trống"
			},
			confirmPassword: {
				equalTo: "Mật khẩu không trùng khớp"
			},
		},

		submitHandler: function(form) {
			var data = new FormData(form);
			$.ajax({

				url: '/updateClient',
				type: "post",
				enctype: 'multipart/form-data',
				data: data,
				processData: false,
				contentType: false,
				cache: false,
				success: function(result) {
			
						window.location.replace("http://localhost:8080/home")
						alert('Đăng kí thành công');
				},
				error: function(error) {
					alert('Đã có lỗi xảy ra !');
				}
			});
		}
	});

