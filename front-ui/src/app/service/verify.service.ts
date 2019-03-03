
export class VerifyService {

    isValid = true;

    gtZero(val: number): string {

        console.log('gtZero:', val);

        if (val <= 0) {
            this.isValid = false;
            return 'error';
        }
        this.isValid = this.isValid && true;
        return 'success';
    }

    isNotEmpty(val: string): string {

        console.log('isNotEmpty:', val);

        if (val === '') {
            this.isValid = false;
            return 'error';
        }
        this.isValid = this.isValid && true;
        return 'success';
    }

    isMoney(val: string): string {
        const moneyReg = /^[1-9]\d*(\.\d{1,2})?$/g;

        if (moneyReg.test(val)) {
            this.isValid = this.isValid && true;
            return 'success';
        }
        this.isValid = false;
        return 'error';
    }
}
