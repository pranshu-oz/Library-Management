function navbarActive() {
    console.log("Navbar active function called");
    const links = document.querySelectorAll('.nav-link');
    links.forEach(link => {
        link.classList.remove('active');
    });
    event.currentTarget.classList.add('active');
}