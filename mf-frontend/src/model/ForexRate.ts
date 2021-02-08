export default class ForexRate {
  private _base: string;
  private _target: string;
  private _value: number;
  private _date: Date;

  constructor(base: string, target: string, value: number, date: Date) {
    this._base = base;
    this._target = target;
    this._value = value;
    this._date = date;
  }

  get base(): string {
    return this._base;
  }

  set base(value: string) {
    this._base = value;
  }

  get target(): string {
    return this._target;
  }

  set target(value: string) {
    this._target = value;
  }

  get value(): number {
    return this._value;
  }

  set value(value: number) {
    this._value = value;
  }

  get date(): Date {
    return this._date;
  }

  set date(value: Date) {
    this._date = value;
  }
}
