import {AbstractControl, ValidationErrors} from "@angular/forms";

export class PositiveNumberValidator {
  static mustBePositive(control : AbstractControl) : ValidationErrors {
    if (control.value && (control.value as number) < 0) {
        return {isPositive: false};
    }
    return {};
  }
}
