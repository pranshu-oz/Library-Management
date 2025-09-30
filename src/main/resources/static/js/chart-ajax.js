window.onload = ajaxCallForChartData;
const ctx = document.getElementById('bookChart').getContext('2d');

function ajaxCallForChartData() {
    fetch('/book/week-stats')
        .then(res => res.json())
        .then(data => {
            var bookChart = new Chart(ctx, {
                type: 'line',
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
                        barThickness: 40,
                        maxBarThickness: 50,
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
}
