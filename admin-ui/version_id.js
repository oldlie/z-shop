function fomartMoney(origin) {
    const yuan = parseInt(origin / 1000);
    let yu = origin % 1000;
    const jiao = parseInt(yu / 100);
    yu = yu % 100;
    const fen =  parseInt(yu / 10);
    return `${yuan}.${jiao}${fen}`;
}

console.log(fomartMoney(99000));

console.log(new Date().getTime());