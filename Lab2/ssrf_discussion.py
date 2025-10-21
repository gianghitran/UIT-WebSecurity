def ssrf_lab(request):
    if request.user.is_authenticated:
        if request.method == "GET":
            return render(request, "Lab/ssrf/ssrf_lab.html", {"blog": "Read Blog About SSRF"})
        
        else:
            blog_post_slug = request.POST["blog"]
            
            try:
                # Lọc ký tự đặc biệt
                if not re.fullmatch('[a-zA-Z0-9_]{1,50}', blog_post_slug):
                    raise ValueError()

                dirname = os.path.dirname(__file__)
                #không cho lấy biến path trực tiếp từ người dùng
                # -> cố định path, chỉ cho đổi tên tệp (button setting)
                file_patch = f"templates/Lab/ssrf/blogs/{blog_post_slug}.txt"
                filename = os.path.join(dirname, file_patch)
                file = open(filename, "r")
                data = file.read()
                return render(request, "Lab/ssrf/ssrf_lab.html", {"blog": data})
            
            except:
                return render(request, "Lab/ssrf/ssrf_lab.html", {"blog": "No blog found"})
    
    else:
        return redirect('login')