window.onload = ajaxCallForChartData;
const ctx = document.getElementById('bookChart').getContext('2d');

function ajaxCallForChartData() {
    fetch('/book/week-stats')
        .then(res => res.json())
        .then(data => {
            var bookChart = new Chart(ctx, {
                type: 'bar',
                data: {
                    labels: data.days,
                    datasets: [{
                        label: 'Books Borrowed',
                        data: data.borrow,
                        borderWidth: 1,
                        borderJoinStyle: 'round',
                        borderRadius: 5,
                        pointStryle: 'circle'
                    }]
                },
                options: {
                    scales: {
                        x: {
                            grid: {
                                display: false
                            }
                        },
                        y: {
                            beginAtZero: true
                        }
                    }
                }
            });
        })
        .catch(error => console.error('Error fetching data:', error));

    fetch('/book/available-stats')
        .then(res => res.json())
        .then(data => {

            const ctx2 = document.getElementById('availableChart').getContext('2d');

            var availableChart = new Chart(ctx2, {
                type: 'bar',
                data: {
                    labels: data.title,
                    datasets: [{
                        label: 'Books Available',
                        data: data.copy,
                        borderWidth: 1,
                        borderRadius: 5,
                        barThickness: 30,
                        maxBarThickness: 50,
                        pointStryle: 'circle',
                    }]
                },
                options: {
                    scales: {
                        x: {
                            grid: {
                                display: false
                            }
                        },
                        y: {
                            beginAtZero: true

                        }
                    }
                }
            });
        })
        .catch(error => console.error('Error fetching data:', error));

    fetch('/book/category')
        .then(res => res.json())
        .then(data => {
            const ctx3 = document.getElementById('pieChart').getContext('2d');
            var pieChart = new Chart(ctx3, {
                type: 'pie',
                data: {
                    labels: data.title,
                    datasets: [{
                        label: 'Book Categories',
                        data: data.value,
                        borderWidth: 1,
                        borderRadius: 5,
                        pointStryle: 'circle',
                        hoverOffset: 30,
                    }]
                },
                options: {
                    responsive: true,
                    scales: {
                        x: {
                            grid: {
                                display: false
                            },
                            ticks: {
                                display: false
                            }
                        },
                        y: {
                            beginAtZero: true,
                            grid: {
                                display: false
                            },
                            ticks: {
                                display: false
                            }
                        }
                    },
                    plugins: {
                        legend: {
                            display: false
                        },
                        tooltip: {
                            enabled: true
                        },
                        title: {
                            display: true,
                            text: 'Book Categories',
                            font: { size: 18 }
                        }
                    }
                }
            });
        })
        .catch(error => console.error('Error fetching data:', error));

    fetch('/library/total-user')
        .then(res => res.json())
        .then(data => {

            var dougnetChart = new Chart(document.getElementById('dougnetChart'), {
                type: 'doughnut',
                data: {
                    labels: data.member,
                    datasets: [{
                        label: 'Library Stats',
                        data: data.user,
                        borderWidth: 1,
                        borderRadius: 5,
                        pointStryle: 'circle',
                        backgroundColor: [
                            'rgba(54, 162, 235, 0.7)',
                            'rgba(255, 206, 86, 0.7)'
                        ],
                        hoverOffset: 30
                    }]
                },
                options: {
                    responsive: true,
                    plugins: {
                        title: {
                            display: true,
                            text: 'Library Users',
                            font: { size: 18 }
                        },
                        legend: {
                            display: false
                        }
                    }
                }
            });

        })
        .catch(error => console.error('Error fetching data:', error));
}
