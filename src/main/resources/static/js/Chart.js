async function getEstatisticas() {

    let pergunta = Number(document.getElementById('opcao').value);
    try {
        const response = await fetch(`http://localhost:8080/${pergunta}`);
        const json = await response.json();
        await gerarGrafico(json);
    } catch (error) {
        console.error(error);
    }
    async function gerarGrafico(json) {
        const pergunta = json.pergunta;
        const estatisticas = json.estatisticas;

        const labels = [];
        const valores = [];
        estatisticas.forEach((item) => {
            const chave = Object.keys(item)[0];
            const valor = item[chave];
            labels.push(chave);
            valores.push(valor);
        });
        const ctx = document.getElementById('grafico').getContext('2d');
        const chart = new Chart(ctx, {
            type: 'horizontalBar',
            data: {
                labels: labels,
                datasets: [{
                    label: pergunta,
                    data: valores,
                    backgroundColor: 'rgba(54, 162, 235, 0.2)',
                    borderColor: 'rgba(54, 162, 235, 1)',
                    borderWidth: 1
                }]
            }
        });
        // código para gerar o gráfico
        window.location.href = 'grafico.html'; // redireciona para a página de resultados
    }
}



// function carregarPagina() {
//     const formulario = document.querySelector('form');
//
//     formulario.addEventListener('submit', (evento) => {
//         evento.preventDefault();
//         const opcao = document.querySelector('#opcoes').value;
//         const url = `http://localhost:8080/rest/estatisticas/?pergunta=${opcao}`;
//         gerarGrafico(url);
//     });
// }
// async function gerarGrafico(url) {
//     const response = await fetch(url);
//     const json = await response.json();
//
//     const labels = [];
//     const data = [];
//
//     for (const estatistica of json.estatisticas) {
//         const label = Object.keys(estatistica)[0];
//         const valor = Object.values(estatistica)[0];
//
//         labels.push(label);
//         data.push(valor);
//     }
//
//     const ctx = document.getElementById('grafico').getContext('2d');
//     const chart = new Chart(ctx, {
//         type: 'bar',
//         data: {
//             labels: labels,
//             datasets: [
//                 {
//                     label: json.pergunta,
//                     data: data,
//                     backgroundColor: ['#ff6384', '#36a2eb', '#cc65fe', '#ffce56'],
//                 },
//             ],
//         },
//     });
// }
//
//
//
//
//
